var tarefa = new Vue({
    el: '#tarefa',
    data: {
        tarefas: [],
        tarefa: {},
        log: {},
        logs: {},
        loading: false
    },
    methods: {
        getTarefas: function(){
            this.loading = true;
            this.$http.get(window.baseUrl+"tarefa/list/").then(function(resp){
                this.tarefas = resp.data;
                this.loading = false;
            }, function(err){
                this.loading = false;
            })
        },
        novaTarefa: function(){
            this.tarefa = {};
            this.tarefa.porcentagem = 0
            this.tarefa.dataLimite = moment().add(1, 'days').format("DD/MM/YYYY")
            $("#formTarefa").modal('show');
        },
        filtraTarefa: function(){
            this.loading = true;
            this.$http.get(window.baseUrl+"tarefa/filter/").then(function(resp){
                    this.tarefas = resp.data;
                    this.loading = false;
            }, function(resp){
                    console.info(error)
            })

        },
        salvarTarefa: function(){
            this.$http.post(window.baseUrl+"tarefa/save", this.tarefa).then(function(resp){
                this.getTarefas();
                $("#formTarefa").modal('hide');
                this.tarefa = {};
            }, function(error){
                console.info(error)
            })
        },
        editTarefa: function(tarefa){
            this.$http.get(window.baseUrl+"tarefa/show/"+tarefa.id).then(function(resp){
                this.tarefa = resp.data;
                $("#formTarefa").modal('show');
            }, function(resp){
            })
        },
        novoLog: function(tarefa){
            this.$http.get(window.baseUrl+"tarefa/showTarefaLog/"+tarefa.id).then(function(resp){
                this.tarefa = resp.data;
                this.log = {};
                $("#formLog").modal('show');
            }, function(resp){
            })
        },
        updateTarefa: function(){
            this.$http.put(window.baseUrl+"tarefa/update/"+this.tarefa.id, this.tarefa).then(function(resp){
                this.getTarefas();
                $("#formTarefa").modal('hide');
                this.tarefa = {};
            }, function(resp){
            })
        }
    },
    ready: function(){
        this.getTarefas();
    }
});