(function() {
    var app = angular.module('blog', []);

    app.controller('HelloController', ['$http', function($http) {
        var hello = this;
        hello.users = {};
        $http.get('http://localhost:8010/rest/users').success(function(data) {
            hello.users = data._embedded.userList;
            console.log(hello.users);
        });
    }]);
})();