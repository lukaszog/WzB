/**
 * Created by Promar on 03.11.2016.
 */

app.controller('TraderOperation', ['$scope', '$http', '$route', 'TraderService', function ($scope, $http, $route, TraderService) {

    $scope.form = {};
    $scope.names = ["STA", "STB", "STC",
        "STE", "STX"];

    $scope.reloadRoute = function () {
        $route.reload();
    };

    $scope.createTrader = function () {
        var name = $scope.form.nameTrader;
        var surname = $scope.form.surnameTrader;
        var numberTrader = $scope.form.numberTrader;
        var nameTeam;

        if ($scope.nameTeam == 'STA') {
            nameTeam = 'STA';
        } else if ($scope.nameTeam == 'STB') {
            nameTeam = 'STB';
        } else if ($scope.nameTeam == 'STC') {
            nameTeam = 'STC';
        } else if($scope.nameTeam == 'STE') {
            nameTeam = 'STE';
        }else{
            nameTeam = 'STX';
        }

        TraderService.addTrader(name, surname, nameTeam, numberTrader);
    };


}]);