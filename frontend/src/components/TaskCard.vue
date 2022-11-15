<template>
    <div :class="['task-container', task.status? 'task-completed' : '']">
        <span> {{task.title}} </span>

        <span class="deadline-container"> {{task.deadline}}</span>

        <div class="buttons-container">
            <button @click="completeTask" class="complete-button" title="Completar tarefa"> <img src="../assets/complete-icon.png" alt="Completar tarefa"> </button>
            <!-- <button @click="remoteTask" class="info-button" title="Sobre a tarefa"> <img src="../assets/info-icon.png" alt="Sobre a tarefa"> </button> -->
            <button @click="remoteTask" class="remove-button" title="Remover tarefa"> <img src="../assets/delete-icon.png" alt="Remover tarefa"> </button>
        </div>
    </div>


</template>

<script>
export default {
    name: "TaskCard",
    emits: ['complete', 'remove'],
    components: {

    },
    props: {
        task: {
            type: Object,
            required: true
        }
    },
    methods: {
        completeTask(){
            this.$emit('complete', this.task.id);
        },

        remoteTask(){
            this.$emit('remove', this.task.id);
        }
    }
}
</script>

<style scoped>
.task-container{
    background-color: #444;
    margin: 8px 0;
    padding: 15px 20px;
    display: flex;
    border-radius: 5px;
    justify-content: space-between;
    color: #eee;
    align-items: center;
}

.buttons-container{
    display: flex;
    flex-direction: row;
    gap: 5px;
    justify-content: space-between;
}

.task-completed{
    border-left: 6px solid chartreuse;
}

.remove-button{
    background-color: #444;
    border: none;
    color: chartreuse;
    cursor: pointer;
}

.info-button{
    background-color: #444;
    border: none;
    color: chartreuse;
    cursor: pointer;
}

.complete-button{
    background-color: #444;
    border: none;
    color: chartreuse;
    cursor: pointer;    
}

.deadline-container{
    color: #eee;
    font-size: 8pt;
    align-self: center;
}
</style>