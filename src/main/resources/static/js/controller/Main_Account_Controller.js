/**
 * Created by Promar on 07.11.2016.
 */


app.controller('MainAccountCtrl', ['$scope', '$http', '$rootScope', 'UserAccountService', 'ngDialog','HOST', function ($scope, $http, $rootScope, UserAccountService, ngDialog, HOST) {

    $scope.notActiveAccounts = [];
    $scope.editData = {};
    $scope.activeAccounts = [];

    $http({
        method: 'GET',
        url: HOST + '/myAccount/active_account',

        headers: {'Content-type': 'application/json'},
    }).success(function (data) {
        $scope.activeAccounts = data;

    }).error(function (data) {
        console.log('Nie udało pobrać się użytkowników.');

    });

    $http({
        method: 'GET',
        url: HOST + '/myAccount/find_notactive_account',

        headers: {'Content-type': 'application/json'},
    }).success(function (data) {
        $scope.notActiveAccounts = data;

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

    $scope.blockAccount = function () {
        $rootScope.username = $scope.editData.accounts.username;

        ngDialog.open({
            template: 'Block',
            controller: 'MainAccountCtrl',
            className: 'ngdialog-theme-default'
        });

    };

    $scope.activeThisAccount = function () {

        UserAccountService.doneActiveAccount($scope.username);
    };

    $scope.giveRoleAdmin = function () {
        $rootScope.username = $scope.editData.accounts.username;

        ngDialog.open({
            template: 'Admin',
            controller: 'MainAccountCtrl',
            className: 'ngdialog-theme-default'
        });

    };

    $scope.doneGiveRoleAdmin = function () {

        console.log('siema');

        $http({
            method: 'POST',
            url: HOST + '/myAccount/give_admin',
            data: $rootScope.username,
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {

            }).error(function (data) {

            ngDialog.open({
                template: 'errorAddClient',
                controller: 'ClientOperation',
                className: 'ngdialog-theme-default'
            });

        });

    };

    $scope.blockAccountUser = function () {

        UserAccountService.doneBlockAccount($scope.username);
    };

}]);