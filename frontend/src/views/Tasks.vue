<template>
    <div class="container">
        <h2 class="title">Menu de Tarefas</h2>
        <form class="add-task-container" @submit.prevent="addTask()">
            <label for="titulo">Título</label>
            <input type="text" class="add-task-input" v-model="taskTitle" placeholder="Título da tarefa" id="titulo" required>

            <label for="descricao">Descrição</label>
            <textarea name="descricao" cols="8" rows="5" class="add-task-textarea" placeholder="Descrição para a tarefa" 
                id="descricao"
                required
                v-model="taskDescription">
            </textarea>

            <div class="date-priority-container">
                <div class="date-container">
                    <label for="data-limite">Data limite</label>
                    <input type="date" id="data-limite" class="add-task-datepicker" v-model="taskDeadline" :min="minDate" required>
                </div>

                <div class="select-container">
                    <label for="prioridade">Prioridade</label>
                    <select id="prioridade" class="add-task-select" v-model="taskPriority">
                        <option value="HIGH">Alta</option>
                        <option value="MEDIUM">Média</option>
                        <option value="LOW">Baixa</option>

                    </select>
                </div>
            </div>
            <input type="submit" value="Adicionar" class="add-task-button">
        </form>

        <task-card v-for="task of tasks" :key="task.id" :task="task" @change="changeTaskStatus(id = $event)"
            @remove="removeTask(id = $event)">
        </task-card>
    </div>
</template>

<script>
import taskCard from '@/components/TaskCard.vue'
import axiosReorganize from '@/settings/axiosInstances';

export default {
    name: "Tasks",
    components: {
        taskCard
    },
    async mounted() {
        await this.fetchTasks();
    },
    data() {
        return {
            tasks: [],
            taskTitle: "",
            taskDescription: "",
            taskDeadline: null,
            taskPriority: "MEDIUM",
        }
    },
    methods: {
        validInputs() {
            return this.taskTitle && this.taskDeadline && this.taskPriority && this.taskDescription;
        },

        async fetchTasks() {
            try {
                const res = await axiosReorganize.get('tasks');

                this.tasks = res.data.content;
            } catch (e) {
                console.log(e.message);
            }
        },

        async addTask() {
            const task = {
                title: this.taskTitle,
                description: this.taskDescription,
                deadline: this.taskDeadline,
                priority: this.taskPriority,
                status: "CREATE"
            };

            try {
                await axiosReorganize.post('tasks', task);
                await this.fetchTasks();
            } catch (e) {
                console.log(e.message);
            } finally {
                this.clearFields();
            }
        },

        async removeTask(id) {
            try {
                await axiosReorganize.delete(`tasks/${id}`);
                await this.fetchTasks();
            } catch (e) {
                console.log(e.message);
            }
        },

        clearFields() {
            this.taskTitle = "";
            this.taskDescription = "";
            this.taskDeadline = null;
            this.taskPriority = "MEDIUM";
        },
    },

    computed: {
        minDate() {
            const now = new Date();
            const year = now.getFullYear();
            const month = now.getMonth() + 1;
            const day = now.getDate();

            const textDate = (d) => {
                return d < 10 ? '0' + d: d;
            } 

            return `${textDate(year)}-${textDate(month)}-${textDate(day)}`
        }
    }
}
</script>

<style scoped>
h2.title {
    color: #eee;
}

label {
    color: #eee;
    font-size: 12pt;
    font-weight: bold;
    align-self: flex-start;
}

.container {
    max-width: 500px;
    flex-basis: 400px;
    margin: 75px auto;
    overflow: auto;
    min-height: 300px;
    border: 2px solid chartreuse;
    padding: 30px;
    border-radius: 10px;
}

.add-task-container {
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

.date-priority-container {
    box-sizing: border-box;
    width: 100%;
    display: flex;
    gap: 10px;
    flex-flow: row wrap;
    justify-content: space-between;
}


.add-task-input {
    height: 30px;
    padding: 0 10px;
    border-radius: 5px;
    border: none;
    /* flex: 1; */
    background-color: #444;
    color: #eee;
    font-size: 16px;
}

.add-task-textarea {
    resize: none;
    background-color: #444;
    color: #eee;
    font-size: 16px;
    border-radius: 5px;
}

.date-container {
    display: flex;
    flex-flow: column wrap;
    width: 45%;
    gap: 5px;
}

.add-task-datepicker {
    /* flex-grow: 2; */
    height: 30px;
    width: 100%;
    background-color: #444;
    color: #eee;
    border-radius: 5px;
}

.select-container {
    display: flex;
    flex-flow: column wrap;
    width: 45%;
    gap: 5px;
}

.add-task-select {
    width: 100%;
    height: 35px;

    background-color: #444;
    color: #eee;

    border-radius: 5px;
}

.add-task-button {
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

.add-task-input:focus {
    outline: none;
    border: 2px solid chartreuse
}

.add-task-textarea:focus {
    outline: none;
    border: 2px solid chartreuse;
}

.add-task-datepicker:focus {
    outline: none;
    border: 2px solid chartreuse;
}

.add-task-select:focus {
    outline: none;
    border: 2px solid chartreuse;
}
</style>