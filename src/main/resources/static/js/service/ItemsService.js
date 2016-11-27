/**
 * Created by Promar on 27.11.2016.
 */

app.service('ItemsService', function ($rootScope, $http, HOST) {


    this.findAllItems = function () {
        $http({
            method: 'GET',
            url: HOST + '/findAll_items',
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {
                $rootScope.listItems = data;
                console.log('sukces');

            }).error(function (data) {
            $rootScope.listClient = 'Nie udało się pobrać listy handlowców.'
        });


    };


});
