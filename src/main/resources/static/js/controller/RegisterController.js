/**
 * Created by Promar on 06.11.2016.
 */

app.controller('RegisterCtrl', function ($scope, $http, $location) {

    $scope.registerResponse = {};


    $scope.error = "";
    $scope.registerUser = function () {
        console.log('insta');
        $http({
            method: 'POST',
            url: 'http://localhost:8080/myAccount/create_account',
            data: {
                "username": $scope.register.email,
                "password": $scope.register.password

            },
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {
                // $location.path("/info");
                console.log('udało się');

            }).error(function (data) {
            // $scope.registerResponse = data.Error;
            // console.log($scope.registerResponse);
            console.log('Nie udało się');

        });
    }
})
    .config(function ($mdThemingProvider) {


        $mdThemingProvider.theme('docs-dark', 'default')
            .primaryPalette('yellow')
            .dark();

    });
