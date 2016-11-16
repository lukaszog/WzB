/**
 * Created by Promar on 11.10.2016.
 */

app.controller('MainController', function ($scope, $http, $interval, HOST) {

    $scope.howManyDocument = '0';
    $scope.howManyTraders = '0';
    $scope.howManyClient = '0';

    $scope.loading = true;
    $http.get('/')
        .success(function (data) {
            // Do stuff with data.
        })
        .catch(function (err) {
            // Log error somehow.
        })
        .finally(function () {
            // Hide loading spinner whether our call succeeded or failed.
            $scope.loading = false;
        });

    $http({
        method: 'GET',
        url: HOST + '/howManyDocument',
        headers: {'Content-type': 'application/json'}
    })
        .success(function (data) {

            $scope.howManyDocument = data;

        }).error(function (data) {
        console.log('Nie udało się ');
    });

    $http({
        method: 'GET',
        url: HOST + '/howManyTraders',
        headers: {'Content-type': 'application/json'}
    })
        .success(function (data) {

            $scope.howManyTraders = data;

        }).error(function (data) {
        console.log('Nie udało się ');
    });

    $http({
        method: 'GET',
        url: HOST + '/howManyClient',
        headers: {'Content-type': 'application/json'}
    })
        .success(function (data) {

            $scope.howManyClient = data;

        }).error(function (data) {
        console.log('Nie udało się ');
    });



    $interval(function () {
        $http({
            method: 'GET',
            url: HOST + '/howManyDocument',
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {

                $scope.howManyDocument = data;

            }).error(function (data) {
            console.log('Nie udało się ');
        });

        $http({
            method: 'GET',
            url: HOST + '/howManyTraders',
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {

                $scope.howManyTraders = data;

            }).error(function (data) {
            console.log('Nie udało się ');
        });

        $http({
            method: 'GET',
            url: HOST + '/howManyClient',
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {

                $scope.howManyClient = data;

            }).error(function (data) {
            console.log('Nie udało się ');
        });




    }, 10000);


});