/**
 * Created by Promar on 04.11.2016.
 */

app.controller('AdminController', function ($scope, $http, documentWZ) {

    $scope.correct = '0';



    $http({
        method: 'GET',
        url: 'http://localhost:8080/find_correct',
        headers: {'Content-type': 'application/json'}
    })
        .success(function (data) {
            $scope.correct = data.length;

        }).error(function (data) {
        console.log('Nie udało się ');
    });

    $scope.findAllCorrect = function () {
        documentWZ.correctWZAll();
    };



});