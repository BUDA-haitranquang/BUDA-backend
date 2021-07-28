const urlApi = 'http://localhost:8080/api/user' //test: https://jsonplaceholder.typicode.com/users/
function getApi(){
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    let requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
    };
    
    fetch(urlApi, requestOptions)
    .then(response => response.text())
    .then(result => console.log(result))
    .catch(error => console.log('error', error));

    return <p>API fetched</p>
}

function postApi(data){
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    let requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: JSON.stringify(data),
        redirect: 'follow'
    };

    fetch(urlApi, requestOptions)
    .then(response => response.text())
    .then(result => {console.log('Success: ', result)})
    .catch(error => console.log('error: ', error));

    return <p>API posted</p>
}
function patchApi(data){
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    let requestOptions = {
        method: 'PATCH',
        headers: myHeaders,
        body: JSON.stringify(data),
        redirect: 'follow'
    };

    fetch(urlApi, requestOptions)
    .then(response => response.text())
    .then(result => {console.log('Success: ', result)})
    .catch(error => console.log('error: ', error));

    return <p>API posted</p>
}

function deleteApi(id){
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    let requestOptions = {
        method: 'DELETE',
        headers: myHeaders,
        redirect: 'follow'
    };

    fetch(`${urlApi}/id/${id}`, requestOptions)
    .then(response => response.text())
    .then(result => {console.log('Success: ', result)})
    .catch(error => console.log('error: ', error));

    return <p>API posted</p>
}
export{
    getApi,
    postApi,
    patchApi,
    deleteApi
}
export default getApi;
