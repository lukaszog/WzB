/**
 * Created by Promar on 07.11.2016.
 */


app.controller('MainAccountCtrl', ['$scope', '$http', '$rootScope', 'UserAccountService','ngDialog', function ($scope, $http, $rootScope, UserAccountService, ngDialog) {

    $scope.accounts = [];
    $scope.editData = {};

    $http({
        method: 'GET',
        url: 'http://localhost:8080/myAccount/find_notactive_account',

        headers: {'Content-type': 'application/json'},
    }).success(function (data) {
        $scope.accounts = data;

    }).error(function (data) {
        console.log('Nie udało pobrać się użytkowników.');

    });

    $scope.activeAccount = function () {

        $rootScope.username = $scope.editData.accounts.username;

        ngDialog.open({
            template: 'Active',
            controller: 'MainAccountCtrl',
            className: 'ngdialog-theme-default'
        });

    };

    $scope.activeThisAccount = function () {

        UserAccountService.doneActiveAccount($scope.username);
    };

}]);