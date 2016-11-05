/**
 * Created by Promar on 12.10.2016.
 */

app.controller('findDocument', function ($scope, $http, $rootScope, $route, documentWZ, ngDialog) {

    $scope.form = {};
    $scope.empty = [];
    $scope.info = '';
    $scope.editData = {};

    $scope.reloadRoute = function () {
        $route.reload();

    };

    $scope.correctBy = function () {
        $rootScope.numberWZtoByCorrect = $scope.editData.documents.numberWZ;
        $rootScope.subProByCorrect = $scope.editData.documents.subProcess;


        ngDialog.open({
            template: 'ByCorrect',
            controller: 'findDocument',
            className: 'ngdialog-theme-default'
        });
    };

    $scope.clickToDelete = function () {
        $rootScope.numberWZtoDelete = $scope.editData.documents.numberWZ;
        $rootScope.subProDelete = $scope.editData.documents.subProcess;


        ngDialog.open({
            template: 'templateId',
            controller: 'findDocument',
            className: 'ngdialog-theme-default'
        });

    };

    $scope.correctDone = function () {

        console.log('jestem tutaj');

        documentWZ.correctWZ($scope.numberWZtoByCorrect, $scope.subProByCorrect);

    };

    $scope.deleteDocument = function () {

        documentWZ.deleteDocument($scope.numberWZtoDelete, $scope.subProDelete);
    };

    $scope.findByNumber = function () {

        var numberWZ = $scope.form.numberDocument;
        var subPro = $scope.form.subProcess;
        documentWZ.findDocumentByNumberWZ(numberWZ, subPro);
    };

    $scope.findByClient = function () {

        var client = $scope.form.nameClient;
        documentWZ.findByClientName(client);
    };

    $scope.findByClientNumber = function () {

        var numberClient = $scope.form.numberClient;
        documentWZ.findByClientNr(numberClient);
    };

    $scope.findByTrader = function () {

        var traderName = $scope.form.nameTrader;
        documentWZ.findByTrader(traderName);
    }

    $scope.findByNameTeam = function () {

        var nameTeam = $scope.form.nameTeam;
        documentWZ.findByNameTeam(nameTeam);

    };

});