<%@ page import="br.edu.unirn.tipos.StatusTarefa" %>
<div class="form-group">
    <label for="titulo" class="col-sm-2 control-label">Titulo</label>
    <div class="col-sm-10">
        <g:field name="titulo" class="form-control" v-model="tarefa.titulo" readonly="true"/>
    </div>
</div>
<div class="form-group">
    <label for="texto" class="col-sm-2 control-label">Texto</label>
    <div class="col-sm-10">
        <g:textArea name="texto" class="form-control" v-model="tarefa.texto" readonly="true"/>
    </div>
</div>
<div class="form-group">
    <label for="usuarioAbertura" class="col-sm-2 control-label">Usuario Abertura</label>
    <div class="col-sm-10">
        <g:field name="usuarioAbertura" class="form-control" v-model="tarefa.usuarioAbertura" readonly="true"/>
    </div>
</div>
<div class="form-group">
    <label for="usuarioResponsavel" class="col-sm-2 control-label">Usuario Responsavel</label>
    <div class="col-sm-10">
        <g:field name="usuarioResponsavel" class="form-control" v-model="tarefa.usuarioResponsavel" readonly="true"/>
    </div>
</div>
<div class="form-group">
    <label for="dataLimite" class="col-sm-2 control-label">Data Limite</label>
    <div class="col-sm-10">
        <g:field name="dataLimite" class="form-control" v-model="tarefa.dataLimite" readonly="true"/>
    </div>
</div>
<div class="form-group">
    <label for="tipoTarefa" class="col-sm-2 control-label">Tipo Tarefa</label>
    <div class="col-sm-10">
        <g:field name="tipoTarefa" class="form-control" v-model="tarefa.tipoTarefa" readonly="true"/>
    </div>
</div>
<div class="form-group">
    <label for="statusTarefa" class="col-sm-2 control-label">Status Tarefa</label>
    <div class="col-sm-10">
        <g:field name="statusTarefa" class="form-control" v-model="tarefa.statusTarefa" readonly="true"/>
    </div>
</div>
<div class="form-group">
    <label for="porcentagem" class="col-sm-2 control-label">Porcentagem</label>
    <div class="col-sm-10">
        <g:field type="number" name="porcentagem" class="form-control" v-model="tarefa.porcentagem" readonly="true"/>
    </div>
</div>