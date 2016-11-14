/**
 * Created by Promar on 06.11.2016.
 */
app.service('AuthenticatedService', function ($rootScope, $http, ngDialog, HOST) {

    var self = this;
    $rootScope._username = '';
    $rootScope.userRoles = false;
    $rootScope.logout = false;
    $rootScope.authenticated = false;
    self.credentials = {};
    $rootScope.loginError = false;


    var setUsername = function (username) {
        _username = username;
    };



    this.showUserName = function () {
        return "Jesteś zalogowany jako: "+_username;
    };


    $http({
        method: 'GET',
        url: HOST + '/myAccount/user'
    }).then(function successCallback(response) {
        var data = response.data;
        if(data.name) {
            $rootScope.authenticated = true;
            $rootScope._username = data.name;
            console.log($rootScope._username);
            $http({
                method: 'GET',
                url: HOST + '/myAccount/role'
            }).then(function successCallback(response) {
                $rootScope.userRoles = response.data;

            }, function errorCallback(response) {
                $rootScope.userRoles = false;

            });
        }

    }, function errorCallback(response) {
        $rootScope.authenticated = false;

    });



    this.authenticatedUser = function(credentials, callback) {

        var headers = credentials ? {
            authorization : "Basic "
            + btoa(credentials.username + ":"
                + credentials.password)
        } : {};


        $http.get(HOST + '/myAccount/user', {
            headers : headers
        }).then(function(response) {
            var data = response.data;
            if (data.name) {
                $rootScope.authenticated = true;
                $rootScope._username = data.name;
                $rootScope.admin = data && data.roles && data.roles.indexOf("ROLE_ADMIN")>-1;

                $http({
                    method: 'GET',
                    url: HOST + '/myAccount/role'
                }).then(function successCallback(response) {
                    $rootScope.userRoles = response.data;

                }, function errorCallback(response) {
                    $rootScope.userRoles = false;
                });

        } else {
            self.authenticated = false;
            self.admin = false;
                console.log('tutaj');
        }
            callback && callback(true);
        }, function(err) {
            self.authenticated = false;
            callback && callback(false);

            ngDialog.open({
                template: 'errorLogin',
                controller: 'LoginCtrl',
                className: 'ngdialog-theme-default'
            });

        });
    };
});
