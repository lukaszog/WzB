/**
 * Created by Promar on 11.10.2016.
 */

app.controller('DocumentOperation', ['$scope', '$http','documentWZ',  function ($scope, $http,documentWZ) {


    $scope.createDocument = function () {
        var numberWZ = $scope.form.numberWZ;
        var subProcess = $scope.form.subProcess;
        var client = $scope.form.nameClient;
        var clientNumber = $scope.form.numberClient;
        var nameTrader = $scope.form.nameTrader;
        var date = $scope.form.date;

        documentWZ.addWZ(numberWZ, subProcess, client, clientNumber, nameTrader, date);
    };


    $scope.deleteWZ = function () {
       var numberWZ = $scope.form.numberWZ;
       documentWZ.removeWZ(numberWZ);
    }


}]);