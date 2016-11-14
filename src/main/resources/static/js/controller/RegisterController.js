/**
 * Created by Promar on 06.11.2016.
 */

app.controller('RegisterCtrl', function ($scope, $http, $location, HOST) {

    $scope.registerResponse = {};

    $scope.error = [];
    $scope.showError = [];
    $scope.reponseError = false;
    $scope.userAlreadyExists = false;
    $scope.numberAlreadyExists = false;
    $scope.wrongPass = false;

    $scope.registerUser = function () {

        $http({
            method: 'POST',
            url: HOST + '/myAccount/create_account',
            data: {
                "username": $scope.register.email,
                "password": $scope.register.password,
                "confirmPassword": $scope.register.passwordConfirm,
                "name": $scope.register.name,
                "surname": $scope.register.surname,
                "numberUser": $scope.register.number

            },
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {
                $scope.success = data.Success;
                $scope.error = data.Error;
                $scope.listError = data;



                if ($scope.error == 'ExistsUser') {

                    angular.forEach($scope.listError, function (value, key) {
                        $scope.userAlreadyExists = false;
                        $scope.numberAlreadyExisits = false;
                        $scope.wrongPass = false;
                        $scope.reponseError = false;
                        if (value == 'ExistsUser') {
                            $scope.userAlreadyExists = true;
                            $scope.reponseError = true;
                        }

                    });
               }
                if ($scope.error == 'WrongPass') {
                    $scope.wrongPass = true;
                    $scope.reponseError = true;
                    $scope.numberAlreadyExists = false;
                    $scope.userAlreadyExists = false;
                    console.log('jestem tu');
                }

                if ($scope.error == 'ExistsNumber') {
                    $scope.numberAlreadyExists = true;
                    $scope.userAlreadyExisits = false;
                    $scope.wrongPass = false;
                    $scope.reponseError = true;
                }

                if ($scope.success == 'Create') {
                    $location.path("/after_register");

                }

            }).error(function (data) {
            $scope.showError.push("Problem połączenia z serwerem.");

        });
    }
})
    .config(function ($mdThemingProvider) {


        $mdThemingProvider.theme('docs-dark', 'default')
            .primaryPalette('yellow')
            .dark();

    });
