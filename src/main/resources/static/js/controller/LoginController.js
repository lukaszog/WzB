/**
 * Created by Promar on 05.11.2016.
 */


app.controller('LoginCtrl', ['$rootScope', '$http', '$location', '$route', '$scope','AuthenticatedService',
    function ($rootScope, $http, $location, $route, $scope, AuthenticatedService) {

        var self = this;
        this.credentials = {};

        self.tab = function (route) {
            return $route.current && route === $route.current.controller;
        };

        $scope.taram = function () {
            console.log('taram');
        };

        var authenticated = function (credentials, callback) {
            AuthenticatedService.authenticated(credentials, callback);
        };


        self.login = function () {

            authenticated(self.credentials, function (authenticated) {
                if (authenticated) {
                    $location.path('/#login')
                    self.error = false;
                    $rootScope.authenticated = true;
                    // $scope.userInfo = self.user.username;
                } else {
                    $scope.errorForm = "Błędne dane!";
                    $location.path("/login");
                    self.error = true;
                    $rootScope.authenticated = false;
                }
            })
        };

        $scope.logout = function () {

            $http.post('/logout', {}).success(function () {
                $location.path("/login");
                $rootScope.authenticated = false;
                $rootScope.userRoles = false;

                $rootScope.info = "Zostałeś porawnie wylogowany!";

            }).error(function (data) {
                $rootScope.authenticated = false;
                $rootScope.userRoles = false;
                $rootScope.userInfo = false;
            });
        }

    }])
    .config(function ($mdThemingProvider) {


        $mdThemingProvider.theme('docs-dark', 'default')
            .primaryPalette('yellow')
            .dark();

    });
