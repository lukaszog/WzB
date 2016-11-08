/**
 * Created by Promar on 03.11.2016.
 */

app.service('UserAccountService', function ($rootScope, $http, ngDialog) {



    this.doneActiveAccount = function (username) {
        $rootScope.documents = [];
        $http({
            method: 'PATCH',
            url: 'http://localhost:8080/myAccount/make_active_account',
            data: {
                "username": username
            },
            headers: {'Content-type': 'application/json'}
        }).success(function (data) {
            ngDialog.open({
                template: 'success',
                controller: 'MainAccountCtrl',
                className: 'ngdialog-theme-default'
            });

        }).error(function (data) {

        });
    };

});