/**
 * Created by Promar on 01.11.2016.
 */

app.controller('ShowAllDocuments', ['$scope', '$http','documentWZ',  function ($scope, $http,documentWZ) {

   // documentWZ.showAllDocuments();

        $scope.documents = [];

        $http({
            method: 'GET',
            url: 'http://localhost:8080/showAllDocuments',

            headers: {'Content-type': 'application/json'},
        }).success(function (data) {
            $scope.documents = data;

        }).error(function (data) {
            console.log('Nie udało się pobrać WZ');

        });



}]);