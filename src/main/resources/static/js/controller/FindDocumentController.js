/**
 * Created by Promar on 12.10.2016.
 */

app.controller('findDocument', function ($scope, $http, $rootScope,$route, documentWZ, ngDialog) {

    $scope.form = {};
    $scope.empty = [];
    $scope.info = '';
    $scope.editData = {};

    $scope.reloadRoute = function() {
        $route.reload();
    };

    $scope.editData = {};

    // $scope.Edit = function() {
    //     var id = $scope.editData.documents.numberWZ;
    //     console.log(id);
    // };

    $scope.clickToDelete = function () {
        $rootScope.numberWZtoDelete = $scope.editData.documents.numberWZ;
        $rootScope.subProDelete = $scope.editData.documents.subProcess;

        ngDialog.open({
            template: 'templateId',
            controller: 'findDocument',
            className: 'ngdialog-theme-default'
        });

    };

    $scope.deleteDocument = function () {
        console.log($scope.numberWZtoDelete);
        console.log($scope.subProDelete);
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

});