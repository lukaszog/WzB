/**
 * Created by Promar on 04.11.2016.
 */

app.controller('AdminController', function ($scope, $http, $rootScope, $route, $location, $timeout, documentWZ, ngDialog, HOST) {

    $scope.correct = '0';
    $scope.accountToActive = '0';
    $scope.editData = {};
    $scope.showInfo = false;
    $scope.load = true;
    $rootScope.alertForAdmin = false;

    $timeout(function () {
        $scope.showInfo = true;
        $scope.load = false;
    }, 1500);

    if ($scope.correct > 0) {
        $rootScope.alertForAdmin = true;
    }


    $http({
        method: 'GET',
        url: HOST + '/find_correct',
        headers: {'Content-type': 'application/json'}
    })
        .success(function (data) {
            $scope.correct = data.length;

        }).error(function (data) {
        console.log('Nie udało się ');
    });

    $http({
        method: 'GET',
        url: HOST + '/myAccount/find_notactive_account',
        headers: {'Content-type': 'application/json'}
    })
        .success(function (data) {
            $scope.accountToActive = data.length;
            console.log($scope.accountToActive);

        }).error(function (data) {
        console.log('Nie udało się ');
    });


    $scope.findAllCorrect = function () {
        documentWZ.correctWZAll();
    };

    $scope.clickToDelete = function () {
        $rootScope.numberWZtoDelete = $scope.editData.documents.numberWZ;
        $rootScope.subProDelete = $scope.editData.documents.subProcess;

        ngDialog.open({
            template: 'templateDelete',
            controller: 'ShowAllDocuments',
            className: 'ngdialog-theme-default'
        });

    };

    $scope.deleteDocument = function () {
        documentWZ.deleteDocument($scope.numberWZtoDelete, $scope.subProDelete);
    };

});