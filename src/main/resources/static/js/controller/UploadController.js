/**
 * Created by Promar on 11.12.2016.
 */

app.controller('UploadController', function($scope, FileUploader) {
    console.log("Upload...");
    $scope.uploader = new FileUploader(
        {
            url: 'http://localhost:8080/upload'
        }
    );

    //console.info('uploader', uploader);
});