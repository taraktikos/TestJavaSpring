(function() {
    var app = angular.module('blog', []);

    app.controller('HelloController', function() {
        this.greeting = "test";
    });
})();