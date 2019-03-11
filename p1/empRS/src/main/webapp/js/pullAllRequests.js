
let table = document.getElementById("response-table");
let employeeName = document.getElementById("input_employee_name");

document.getElementById('populate_pending').addEventListener('click', () => pullDown(employeeName.value,'pending')); //different status conditions
document.getElementById('populate_accepted').addEventListener('click', () => pullDown(employeeName.value, 'accepted'));
document.getElementById('populate_rejected').addEventListener('click', () => pullDown(employeeName.value, 'rejected'));


function pullDown(who, status) {
	if (who=='')
		who='current';
	   clearTable();
    let xhr = new XMLHttpRequest();
    
    xhr.onreadystatechange = () => {
        
    	if (xhr.readyState == 4) {
        	let data = JSON.parse(xhr.response);
        	console.log(data);
        	
        	for (let item of data) {
        		
        		let html = `
				<tr>
					<td>${item.id}</td>
					<td>${item.date}</td>
					<td style="text-align: center">${item.employeeId}</td>
					<td>${item.amount}</td>
					<td>${item.description}</td>
					<td>${item.status}</td>
					<td>${item.finalized_by}</td>`
        			if(item.imageString !=  null) {
        				html += `<td><img src="data:image/png;base64,${item.imageString}" width="80" height="120"></td>`
        			}
        			else { 
        				html += `<td><img src="../resources/ReceiptSwiss.jpg" width="80" height="120"></td>`
        			}
        		if(status =="pending") {
						html +=
						`<td><button onclick="FinalizeRequest(${item.id}, 'accepted')" id="accept">Accept</button></td>
						<td><button onclick="FinalizeRequest(${item.id}, 'rejected')" id="reject">Deny</button></td>`;
					}
					html +=`</tr>`;
   
 		console.log( `${item.id}, ${item.imageString}`);
        		table.insertAdjacentHTML('beforeend', html)
        	}
        }
    };
    
    xhr.open('POST','../reimbursement_crud', true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("status="+status+"&crud=read&who="+who);
}

function test(id, status)
{console.log(id, status)}

function FinalizeRequest(id, status) {
	
	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = () => {
        
		if (xhr.readyState == 4) {
        	var data = xhr.response;
			console.log(data);
			pullDown(employeeName.value ,'pending')
		}
	}
	
	xhr.open('POST','../reimbursement_crud', true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("status="+status+"&id="+id+"&crud=update");
}
function clearTable() {
	console.log(table.children.length);
	let children = table.children;
	
	for (let node = children.length-1; node>=0; node--) {
		console.log(children[node]);
		children[node].parentNode.removeChild(children[node]);
	}
}