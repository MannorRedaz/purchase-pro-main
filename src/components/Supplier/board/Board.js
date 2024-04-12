import axios from 'axios'

class BoardJs {
     getAcademyList() {
        return new Promise((resolve, reject) => {
            axios.post("academys").then(res=>{
            resolve(res.data);
        });
     });
    }
    
}
export default  new  BoardJs() ;