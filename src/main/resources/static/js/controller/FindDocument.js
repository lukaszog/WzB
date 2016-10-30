/**
 * Created by Promar on 12.10.2016.
 */

app.controller('findDocument', function($scope, $http, documentWZ) {

    $scope.form= {};
    $scope.empty =[];
    $scope.info ='';

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