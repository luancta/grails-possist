package br.edu.unirn

import grails.converters.JSON

class TarefaController {

    static scaffold = Tarefa

    def tarefasFiltradas

    def index(){}

    def save(){
        params << request.JSON
        Tarefa tarefa = new Tarefa()
        bindData(tarefa, params, [exclude:['dataLimite']])
        tarefa.dataLimite = params.date('dataLimite', 'dd/MM/yyyy')

        if(!tarefa.save(flush: true)){
            tarefa.errors.each {println it}
            render status: 500
            return
        }

        render status: 200
    }

    def show(){
        Tarefa tarefa = Tarefa.get(params.id)
        render([
                id: tarefa.id,
                titulo: tarefa.titulo,
                texto: tarefa.texto,
                usuario: tarefa.usuarioAbertura,
                usuarioAbertura: tarefa.usuarioAbertura.id,
                usuarioResponsavel: tarefa.usuarioResponsavel?.id,
                dataLimite: tarefa.dataLimite.format("dd/MM/yyyy"),
                tipoTarefa: tarefa.tipoTarefa.id,
                statusTarefa: tarefa.statusTarefa.name(),
                porcentagem: tarefa.porcentagem
        ] as JSON)
    }

    def showTarefaLog(){
        Tarefa tarefa = Tarefa.get(params.id)
        render([
                id: tarefa.id,
                titulo: tarefa.titulo,
                texto: tarefa.texto,
                usuarioAbertura: tarefa.usuarioAbertura.email,
                usuarioResponsavel: tarefa.usuarioResponsavel?.email,
                dataLimite: tarefa.dataLimite.format("dd/MM/yyyy"),
                tipoTarefa: tarefa.tipoTarefa.descricao,
                statusTarefa: tarefa.statusTarefa.descricao,
                porcentagem: tarefa.porcentagem
        ] as JSON)
    }

    def list(){
        def retorno = []

        if(tarefasFiltradas)
            tarefasFiltradas.each {
                retorno.add([
                        id: it.id,
                        titulo: it.titulo,
                        usuarioAbertura: it.usuarioAbertura.email,
                        usuario: it.usuarioAbertura,
                        usuarioResponsavel: it.usuarioResponsavel?.email,
                        dataLimite: it.dataLimite.format("dd/MM/yyyy"),
                        tipoTarefa: it.tipoTarefa.descricao,
                        statusTarefa: it.statusTarefa.name(),
                        porcentagem: it.porcentagem
                ])
            }
        else {
            Tarefa.list().each {
                retorno.add([
                        id                : it.id,
                        titulo            : it.titulo,
                        usuarioAbertura   : it.usuarioAbertura.email,
                        usuario           : it.usuarioAbertura,
                        usuarioResponsavel: it.usuarioResponsavel?.email,
                        dataLimite        : it.dataLimite.format("dd/MM/yyyy"),
                        tipoTarefa        : it.tipoTarefa.descricao,
                        statusTarefa      : it.statusTarefa.name(),
                        porcentagem       : it.porcentagem
                ])
            }
        }
        render retorno as JSON
    }

    def filter() {
        log.info("Iniciando buscar por filtros.")

        def tarefaList =  Tarefa.withCriteria(){
            ilike('titulo', '%' + params["titulo"] + '%')
        }

        tarefasFiltradas = tarefaList

        render view: "index", model: tarefaList

    }

    private getFilterList(params) {

        def queryParams = [:]

        if(params["titulo"]) {
            queryParams.put("titulo", params["titulo"])
        }

        if(params["description"]) {
            queryParams.put("description", params["description"])

        }

        if(params["typeDescription"]) {
            queryParams.put("typeDescription", params["typeDescription"])
        }

        if(params["startDate"]) {
            queryParams.put("startDate", params["startDate"])
        }

        if (params["endDate"]) {
            queryParams.put("endDate", params["endDate"])
        }

        if(params["active"]) {
            queryParams.put("active", new Boolean(params["active"]))
        }

        queryParams
    }

    def update(){
        params << request.JSON
        Tarefa tarefa = Tarefa.get(params.id)

        bindData(tarefa, params, [exclude:['dataLimite']])
        tarefa.dataLimite = params.date('dataLimite', 'dd/MM/yyyy')

        if(!tarefa.save(flush: true)){
            render status: 500
            return
        }

        render status: 200
    }
}