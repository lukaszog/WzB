/**
 * Created by Promar on 06.11.2016.
 */
app.service('AuthenticatedService', function ($rootScope, $http, $location ) {

    var self = this;
    var _username = '';
    $rootScope.userRoles = false;
    $rootScope.logout = false;

    var setUsername = function (username) {
        _username = username;
    };

    this.showUserName = function () {
        return "Jesteś zalogowany jako: "+_username;
    };

    $http({
        method: 'GET',
        url: 'http://localhost:8080/myAccount/user'
    }).then(function successCallback(response) {
        var data = response.data;
        $rootScope.authenticated = true;
        setUsername(data.name);
        if(data.name=='ADMIN'){
            $rootScope.userRoles = true;
            console.log("Zmieniam");
        }

    }, function errorCallback(response) {
        $rootScope.authenticated = false;

    });

    this.authenticated = function(credentials, callback) {

        var headers = credentials ? {
            authorization : "Basic "
            + btoa(credentials.username + ":"
                + credentials.password)
        } : {};


        $http.get('http://localhost:8080/myAccount/user', {
            headers : headers
        }).then(function(response) {
            var data = response.data;
            if (data.name) {
                $rootScope.authenticated = true;
                setUsername(data.name);
                $rootScope.admin = data && data.roles && data.roles.indexOf("ROLE_ADMIN")>-1;

                if(data.name=='ADMIN'){
                    $rootScope.userRoles = true;
                }

            } else {
                self.authenticated = false;
                self.admin = false;
            }
            callback && callback(true);
        }, function() {
            self.authenticated = false;
            callback && callback(false);
        });
    };

    self.credentials = {};


});
