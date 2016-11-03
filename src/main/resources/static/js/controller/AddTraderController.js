/**
 * Created by Promar on 03.11.2016.
 */

app.controller('TraderOperation', ['$scope', '$http', 'TraderService', function ($scope, $http, TraderService) {

    $scope.form = {};
    $scope.names = ["STA", "STB", "STC",
        "STE"];

    $scope.createTrader = function () {
       var name = $scope.form.nameTrader;
       var surname = $scope.form.surnameTrader;


        var nameTeam;

        if($scope.nameTeam=='STA'){
            nameTeam = 'STA';
        }else if($scope.nameTeam=='STB'){
            nameTeam = 'STB';
        }else if($scope.nameTeam=='STC'){
            nameTeam = 'STC';
        }else{
            nameTeam = 'STE';
        }

        TraderService.addTrader(name, surname, nameTeam);
    };


}]);