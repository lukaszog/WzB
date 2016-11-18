/**
 * Created by Promar on 17.11.2016.
 */
(function () {
app.controller('DemoCtrl', function ($http, $scope,  $timeout, $q, $log, HOST) {
    var self = this;
    $scope.listClient = '';
    var allStates = '';
    $scope.test = 'hej';

    $http({
        method: 'GET',
        url: HOST + '/all_client',
        headers: {'Content-type': 'application/json'}
    })
        .success(function (data) {
             $scope.listClient = data;






        }).error(function (data) {
        $scope.listClient = 'Nie udało się pobrać listy klientów.'
    });

    self.simulateQuery = false;
    self.isDisabled    = false;

    // list of `state` value/display objects
    self.states        = loadAll();
    self.querySearch   = querySearch;
    self.selectedItemChange = selectedItemChange;
    self.searchTextChange   = searchTextChange;

    self.newState = newState;

    function newState(state) {
        alert("Sorry! You'll need to create a Constitution for " + state + " first!");
    }

    // ******************************
    // Internal methods
    // ******************************

    /**
     * Search for states... use $timeout to simulate
     * remote dataservice call.
     */
    function querySearch (query) {
        var results = query ? self.states.filter( createFilterFor(query) ) : self.states,
            deferred;
        if (self.simulateQuery) {
            deferred = $q.defer();
            $timeout(function () { deferred.resolve( results ); }, Math.random() * 1000, false);
            return deferred.promise;
        } else {
            return results;
        }
    }

    function searchTextChange(text) {
        $log.info('Text changed to ' + text);
    }

    function selectedItemChange(item) {
        $log.info('Item changed to ' + JSON.stringify(item));
    }

    /**
     * Build `states` list of key/value pairs
     */
    angular.forEach($scope.listClient, function (value, key) {


        allStates = allStates + value.abbreviationName + ' , ';

    });


    console.log($scope.listClient.abbreviationName);

    function loadAll() {

        angular.forEach($scope.listClient, function (value, key) {


            $scope.test = $scope.test + value.abbreviationName + ' , ';

        });

        console.log('s'+$scope.test);
        allStates = $scope.test;



        return allStates.split(/, +/g).map( function (state) {
            return {
                value: state.toLowerCase(),
                display: state
            };
        });
    }


    /**
     * Create filter function for a query string
     */
    function createFilterFor(query) {
        var lowercaseQuery = angular.lowercase(query);

        return function filterFn(state) {
            return (state.value.indexOf(lowercaseQuery) === 0);
        };

    }
});
})();

