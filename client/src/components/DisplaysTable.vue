<template>
    <section>
      <table id="display-table">
        <thead>
          <tr>
            <th>Drug</th>
            <th>Drug Class</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="display in filteredDisplays" :key="display.id">
            <td>
              <router-link
                v-if="display.drug_name"
                :to="{ name: 'DrugDetail', params: { id: display.id } }"
              >
                {{ display.drug_name }}
              </router-link>
            </td>
            <td>
              <router-link
                v-if="display.classes_name"
                :to="{ name: 'ClassDetail', params: { id: display.class_id } }"
              >
                {{ display.classes_name }}
              </router-link>
            </td>
          </tr>
        </tbody>
      </table>
    </section>
  </template>
  
  
  <script>
export default {
  computed: {
    filteredDisplays() {
      console.log("Current choice ID:", this.$store.state.currentChoiceId);
      console.log("Drugs in store:", JSON.stringify(this.$store.state.drugs));
      console.log("Classes in store:", JSON.stringify(this.$store.state.classes));

      let filtered = [];
      const currentChoiceId = this.$store.state.currentChoiceId;
      if (currentChoiceId === 1) {
        filtered = this.$store.state.drugs;
      } else if (currentChoiceId === 2) {
        filtered = this.$store.state.classes.map(cls => ({
          id: cls.id,
          classes_name: cls.class_name,
          drug_name: null,
        }));
      } else {
        filtered = this.$store.state.drugs.filter(drug => drug.class_id === currentChoiceId);
      }
      console.log("Filtered results:", JSON.stringify(filtered));
      return filtered;
    }
  }
}
</script>

  
  <style>
  #display-table th, #display-table td {
    padding: 10px;
  }
  </style>  