/**
 * Created by Promar on 10.11.2016.
 */

app.controller('UserAccount', function ($scope, $http, $rootScope, $location, documentWZ, ngDialog, HOST) {

    $scope.username = $rootScope._username;
    $scope.infoUsers = '';
    $rootScope.nameUser = '';




    $http({
        method: 'POST',
        url: HOST + '/myAccount/user_info',
        data: {
            "username": $scope.username


        },
        headers: {'Content-type': 'application/json'}
    })
        .success(function (data) {
            $scope.infoUsers = data;
            $rootScope.nameUser = $scope.infoUsers.name;

        }).error(function (data) {
        ngDialog.open({
            template: 'errorAccountUser',
            controller: 'UserAccount',
            className: 'ngdialog-theme-default'
        });

    });


    $scope.findMyDocument = function () {

        documentWZ.findByTrader($scope.infoUsers.surname);

    }

    $scope.changePassword = function () {

        $http({
            method: 'POST',
            url: HOST + '/myAccount/change_password',
            data: {
                "oldPassword": $scope.password.old,
                "newPassword": $scope.password.new,
                "confirmNewPassword": $scope.password.newConfirm,
                "username": $scope.username


            },
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {
                $scope.listError = data;
                $scope.success = data.Success;
                $scope.error = data.Error;
                $scope.wrongPass = false;
                $scope.diffPass = false;

                if ($scope.success == 'newPass') {

                    ngDialog.open({
                        template: 'successAccountUser',
                        controller: 'UserAccount',
                        className: 'ngdialog-theme-default'
                    });
                    $location.path("/account");
                }

                angular.forEach($scope.listError, function (value, key) {
                    $scope.wrongPass = false;
                    $scope.diffPass = false;

                    if (value == 'wrongPass') {
                        $scope.wrongPass = true;

                    } else if (value == 'diffPass') {
                        $scope.diffPass = true;
                    }

                });


            }).error(function (data) {
            console.log("bład");
            ngDialog.open({
                template: 'errorAccountUser',
                controller: 'UserAccount',
                className: 'ngdialog-theme-default'
            });

        });

    };


});