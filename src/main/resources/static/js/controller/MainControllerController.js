/**
 * Created by Promar on 11.10.2016.
 */

app.controller('MainController', function ($scope, $http) {

    $scope.howManyDocument = '0';
    $scope.howManyTraders = '0';
    $scope.howManyClient = '0';

    $http({
        method: 'GET',
        url: 'http://localhost:8080/howManyDocument',
        headers: {'Content-type': 'application/json'}
    })
        .success(function (data) {

            $scope.howManyDocument = data;

        }).error(function (data) {
        console.log('Nie udało się ');
    });

    $http({
        method: 'GET',
        url: 'http://localhost:8080/howManyTraders',
        headers: {'Content-type': 'application/json'}
    })
        .success(function (data) {

            $scope.howManyTraders = data;

        }).error(function (data) {
        console.log('Nie udało się ');
    });

    $http({
        method: 'GET',
        url: 'http://localhost:8080/howManyClient',
        headers: {'Content-type': 'application/json'}
    })
        .success(function (data) {

            $scope.howManyClient = data;

        }).error(function (data) {
        console.log('Nie udało się ');
    });

});