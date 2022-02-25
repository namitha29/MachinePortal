# MachinePortal
Sample VM provisioning/managing Application


API's:

POST    https://localhost:8080/user/register   -- sign up
POST    https://localhost:8080/user/token      -- get token
GET     https://localhost:8080/user            -- list of users along with its id
DELETE  https://localhost:8080/user/{userId}   -- delete user


POST   https://localhost:8080/virtualmachine/provision                   -- create VM
GET    https://localhost:8080/virtualmachine                             -- get list of vM's for a logged in user


GET    https://localhost:8080/virtualmachine/filter?topMemoryCount=3            -- get top 3 vms by memory for loggedin user
GET    https://localhost:8080/virtualmachine/master/filter?topMemoryCount=3     -- get top 3 vms by memory for all users 


Sample input:

User:
{
    "name" : "nami",
    "phoneNumber" : "8971137374"
    "password": "dummypass",
    "email" : "nami@gmail",
    "role" : "MASTER"
}

VirtualMachine:
{
    "os":"linux",
    "ram": 2,
    "hardDisk": 250,
    "cpuCores": 2,
    "name": "vmforAwsTesting"
}

Token:
{
    "username" : "nami",
    "password" : "dummypass"
}

