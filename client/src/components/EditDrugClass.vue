<template>
    <div>
      <div v-if="isAdmin">
        <h3>Create a New Drug Class</h3>
        <input v-model="newDrugClassName" placeholder="Drug Class Name" />
        <button @click="createDrugClass">Create Drug Class</button>
      </div>
  
      <h3>Drug Class List</h3>
      <ul>
        <li v-for="drugClass in drugClasses" :key="drugClass.id">
          {{ drugClass.name }}
          <div v-if="isAdmin">
            <input v-model="drugClass.name" placeholder="Class Name" />
            <button @click="updateDrugClass(drugClass.id)">Update</button>
            <button @click="deleteDrugClass(drugClass.id)">Delete</button>
          </div>
        </li>
      </ul>
    </div>
  </template>
<script>
import { resourceService } from '../services/ResourceService.js';

export default {
  data() {
    return {
      newDrugClassName: '', 
      drugClasses: [], 
    };
  },
  computed: {
    isAdmin() {
      return (
        this.$store.state.user &&
        this.$store.state.user.role &&
        this.$store.state.user.role.includes("ROLE_ADMIN")
      );  
    }
  },
  methods: {
    fetchDrugClasses() {
      resourceService.getClasses()
        .then(response => {
          this.drugClasses = response.data;
        })
        .catch(error => {
          console.error("Error fetching drug classes:", error);
        });
    },
    
    createDrugClass() {
      const drugClass = {
        name: this.newDrugClassName,
      };
      resourceService.createDrugClass(drugClass)
        .then(response => {
          this.drugClasses.push(response.data);
          this.newDrugClassName = '';
        })
        .catch(error => {
          console.error("Error creating drug class:", error);
        });
    },

    updateDrugClass(classId) {
      const drugClass = this.drugClasses.find(c => c.id === classId);
      resourceService.updateDrugClass(classId, drugClass)
        .then(response => {
          console.log("Drug class updated successfully:", response.data);
        })
        .catch(error => {
          console.error("Error updating drug class:", error);
        });
    },

    deleteDrugClass(classId) {
      resourceService.deleteDrugClass(classId)
        .then(() => {
          this.drugClasses = this.drugClasses.filter(c => c.id !== classId);
        })
        .catch(error => {
          console.error("Error deleting drug class:", error);
        });
    }
  },
  created() {
    this.fetchDrugClasses();
  },
};

</script>


<style>

</style>