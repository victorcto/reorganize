<template>
    <div class="container">
        <h2 class="title">Menu de Tarefas</h2>
        <div class="add-task-container">
            <label for="título">Título</label>
            <input type="text" class="add-task-input" v-model="taskTitle" placeholder="Título da tarefa" >

            <label for="descrição">Descrição</label>
            <textarea name="descricao" cols="8" rows="5" class="add-task-textarea" placeholder="Descrição para a tarefa" v-model="taskDescription">
            </textarea>
            
            <div class="date-priority-container">
                <div class="date-container">
                    <label for="data limite">Data limite</label>
                    <input type="date" name="data_limite" class="add-task-datepicker" v-model="taskDeadline" :min="minDate">
                </div>

                <div class="select-container">
                    <label for="prioridade">Prioridade</label>
                    <select name="prioridade" class="add-task-select" v-model="taskPriority">
                        <option value="1">Alta</option>
                        <option value="2">Média</option>
                        <option value="3">Baixa</option>

                    </select>
                </div>
            </div>
            <button class="add-task-button" @click="addTask()">Adicionar</button>
        </div>

        <task-card v-for="task of tasks" 
            :key="task.id" 
            :task="task"
            @complete="completeTask(id = $event)"
            @remove = "removeTask(id = $event)"
        >
        </task-card>
    </div>
</template>

<script>
import taskCard from '@/components/TaskCard.vue'

export default{
    name: "Tasks",
    components: {
        taskCard
    },
    data(){
        return {
            tasks: [],
            taskTitle: "",
            taskDescription: "",
            taskDeadline: null,
            taskPriority: 2,
        }
    },

    methods: {
        isValidTask(task){
            const {title, deadline, priority} = task;

            return title && deadline && priority;
        },

        addTask(){
            const lastId = (this.tasks.length > 0)? this.tasks[this.tasks.length -1].id : 0;

            const task = {
                id: lastId + 1,
                title: this.taskTitle,
                description: this.taskDescription,
                deadline: this.taskDeadline,
                priority: this.taskPriority,
                status: false
            };
            
            if(this.isValidTask(task)){
                this.tasks.push(task)
            }
                
            this.clearFields();
        },

        removeTask(id){
            console.log(id);

            const index = this.tasks.findIndex(task => task.id === id);

            this.tasks.splice(index, 1);
        }, 

        completeTask(id){
            console.log(id);
            const index =this.tasks.findIndex(task => task.id === id);

            this.tasks[index].status = !this.tasks[index].status;
        },

        clearFields(){
            this.taskTitle = "";
            this.taskDescription = "";
            this.taskDeadline = null;
            this.taskPriority = 2;
        },
    },

    computed: {
        minDate(){
            const now = new Date();

            return `${now.getFullYear()}-${now.getMonth()+1}-${now.getDate()}`;
        }
    }
}
</script>

<style scoped>
h2.title{
    color: #eee;
}

label{
    color: #eee;
    font-size: 12pt;
    font-weight: bold;
    align-self: flex-start;
}
.container{
    max-width: 500px;
    flex-basis: 400px;
    margin: 75px auto;
    overflow: auto;
    min-height: 300px;
    border: 2px solid chartreuse;
    padding: 30px;
    border-radius: 10px;
}

.add-task-container{
    box-sizing: border-box;
    padding: 15px;
    /* border: 2px solid chartreuse;
    border-radius: 10px; */
    margin: 15px 0;
    width: 100%;
    display: flex;
    gap: 10px;
    flex-flow: column wrap;
}
.date-priority-container{
    box-sizing: border-box;
    width: 100%;
    display: flex;
    gap: 10px;
    flex-flow: row wrap;
    justify-content: space-between;
}


.add-task-input{
    height: 30px;
    padding: 0 10px;
    border-radius: 5px;
    border: none;
    /* flex: 1; */
    background-color: #444;
    color: #eee;
    font-size: 16px;
}

.add-task-input:focus{
    outline: none;
    border: 2px solid chartreuse
}

.add-task-textarea{
    resize: none;
    background-color: #444;
    color: #eee;
    font-size: 16px;
    border-radius: 5px;
}

.add-task-textarea:focus{
    outline: none;
    border: 2px solid chartreuse;
}

.date-container{
    display: flex;
    flex-flow: column wrap;
    width: 45%;
    gap: 5px;
}
.add-task-datepicker{
    /* flex-grow: 2; */
    height: 30px;
    width: 100%;
    background-color: #444;
    color: #eee;
    border-radius: 5px;
}

.add-task-datepicker:focus{
    outline: none;
    border: 2px solid chartreuse;
}
.select-container{
    display: flex;
    flex-flow: column wrap;
    width: 45%;
    gap: 5px;
}

.add-task-select{
    width: 100%;
    height: 35px;

    background-color: #444;
    color: #eee;

    border-radius: 5px;
}

.add-task-select:focus{
    outline: none;
    border: 2px solid chartreuse;
}

.add-task-button{
    align-self: flex-end;
    margin-left: 10px;
    background-color: chartreuse;
    height: 40px;
    padding: 0 10px;
    border-radius: 5px;
    font-size: 16px;
    color: #444;
    font-weight: bold;
    cursor: pointer;
    border: none; 
}
</style>