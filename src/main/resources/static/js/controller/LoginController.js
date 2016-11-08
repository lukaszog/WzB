/**
 * Created by Promar on 05.11.2016.
 */


app.controller('LoginCtrl', ['$rootScope', '$http', '$location', '$route', '$scope','AuthenticatedService', '$window',
    function ($rootScope, $http, $location, $route, $scope, AuthenticatedService, $window) {

        var self = this;
        this.credentials = {};

        self.tab = function (route) {
            return $route.current && route === $route.current.controller;
        };


        var authenticated = function (credentials, callback) {
            AuthenticatedService.authenticatedUser(credentials, callback);
        };


        self.login = function () {

            authenticated(self.credentials, function (authenticated) {
                if (authenticated) {
                    $location.path('/#login')
                    self.error = false;
                    $rootScope.authenticated = true;

                } else {
                    $scope.errorForm = "Błędne dane!";
                    $location.path("/login");
                    self.error = true;
                    $rootScope.authenticated = false;
                }
            })
        };

        $scope.logout = function () {

            console.log("Jestem");
            $http.post('/logout', {})

                .success(function () {
                $location.path('/login');
                $rootScope.authenticated = false;
                $rootScope.userRoles = false;
                console.log('zostales wyglowowany1');


            }).error(function (data) {
                $location.path('/login');
                $rootScope.authenticated = false;
                $rootScope.userRoles = false;
                $rootScope.userInfo = false;
                console.log('zostales wyglowowany2');
            });
        }

    }])
    .config(function ($mdThemingProvider) {


        $mdThemingProvider.theme('docs-dark', 'default')
            .primaryPalette('yellow')
            .dark();

    });
