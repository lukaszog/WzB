/**
 * Created by Promar on 10.09.2016.
 */
'use strict';

// Declare app level module which depends on views, and components
var app = angular.module('myApp', [
    'ngRoute', 'ngResource', 'ngMaterial', 'ngMessages','tableSort', 'timer'

]).config(function($routeProvider, $httpProvider) {

    $routeProvider
        .when('/', {
            templateUrl: 'views/main.html',
            controller: ''
        })
        .when('/main', {
            templateUrl: 'views/main.html',
            controller: ''
        })
        .when('/search', {
            templateUrl: 'views/search.html',
            controller: 'findDocument'
        })
        .when('/addDocument', {
            templateUrl: 'views/add_document.html',
            controller: 'DocumentOperation'
        })
        .when('/admin', {
            templateUrl: 'views/admin.html',
            controller: ''
        })
        .when('/account', {
            templateUrl: 'views/account.html',
            controller: ''
        })
        .otherwise({redirectTo: '/'});
        
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

});
