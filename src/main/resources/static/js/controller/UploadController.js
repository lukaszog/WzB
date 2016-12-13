/**
 * Created by Promar on 11.12.2016.
 */

app.controller('UploadController', function($scope, $rootScope, $route, FileUploader, HOST, $http, ngDialog) {

    $rootScope.dateUpdate = new Date();
    $rootScope.text = 'siemaaaa';

    $scope.uploader = new FileUploader(
        {
            url: 'http://wzb24.pl/upload'
        }
    );

    $scope.reloadRoute = function () {
        $route.reload();
    };

    $scope.update = function () {

        $http({
            method: 'GET',
            url: HOST + '/save_items',
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {

                ngDialog.open({
                    template: 'updateSuccess',
                    controller: 'ShowAllDocuments',
                    className: 'ngdialog-theme-default'
                });


                }
            ).error(function (data) {
            console.log('nie dodano');
        });

    };
});