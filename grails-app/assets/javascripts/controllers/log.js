var log = new Vue({
    el: '#logtarefa',
    data: {
        logs: [],
        log: {},
        loading: false
    }

        methods: {
            getLogs: function () {
                this.loading = true;
                this.$http.get(window.baseUrl + "log/list/").then(function (resp) {
                    this.logs = resp.data;
                    this.loading = false;
                }, function (err) {
                    this.loading = false;
                })
            },
        },
        ready: function(){
            this.getLogs();
        }
});