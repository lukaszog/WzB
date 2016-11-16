/**
 * Created by Promar on 07.11.2016.
 */


app.controller('MainAccountCtrl', ['$scope', '$http', '$rootScope', '$route', 'UserAccountService', 'ngDialog', 'HOST', function ($scope, $http, $rootScope, $route,  UserAccountService, ngDialog, HOST) {

    $scope.notActiveAccounts = [];
    $scope.editData = {};
    $scope.activeAccounts = [];

    $scope.reloadRoute = function () {
        $route.reload();
    };

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

    $scope.deleteAccount = function () {
        $rootScope.username = $scope.editData.accounts.username;

        ngDialog.open({
            template: 'Delete',
            controller: 'MainAccountCtrl',
            className: 'ngdialog-theme-default'
        });
    };

    $scope.deleteThisAccount = function () {
        UserAccountService.doneDeleteAccount($rootScope.username);
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

    $scope.giveRoleUser = function () {
        $rootScope.username = $scope.editData.accounts.username;

        ngDialog.open({
            template: 'USER',
            controller: 'MainAccountCtrl',
            className: 'ngdialog-theme-default'
        });

    };

    $scope.confirmGiveRoleAdmin = function () {

        $http({
            method: 'POST',
            url: HOST + '/myAccount/give_admin',
            data: $rootScope.username,
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {
                ngDialog.open({
                    template: 'SuccessAddRole',
                    controller: 'ClientOperation',
                    className: 'ngdialog-theme-default'
                });


            }).error(function (data) {

            ngDialog.open({
                template: 'errorAddClient',
                controller: 'ClientOperation',
                className: 'ngdialog-theme-default'
            });

        });

    };

    $scope.confirmRoleUser = function () {
        $http({
            method: 'POST',
            url: HOST + '/myAccount/give_user',
            data: $rootScope.username,
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {
                ngDialog.open({
                    template: 'SuccessAddRole',
                    controller: 'ClientOperation',
                    className: 'ngdialog-theme-default'
                });


            }).error(function (data) {

            ngDialog.open({
                template: 'errorAddClient',
                controller: 'ClientOperation',
                className: 'ngdialog-theme-default'
            });

        });
    };

    $scope.giveRoleUser = function () {
        $rootScope.username = $scope.editData.accounts.username;

        ngDialog.open({
            template: 'User',
            controller: 'MainAccountCtrl',
            className: 'ngdialog-theme-default'
        });
    };

    $scope.blockAccountUser = function () {

        UserAccountService.doneBlockAccount($scope.username);
    };

}]);