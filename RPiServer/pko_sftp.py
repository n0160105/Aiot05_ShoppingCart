# need install packages
# sudo apt install libffi-dev
# sudo pip3 install paramiko

import paramiko
import sys
from pathlib import Path

def upload(con,lfile,rfile):
    """
    con      : ssh connection 
    lfile    : local filename to process
    rfile    : remote filename to process
    """
    print("Uploading {}".format(lfile))
    ftp_client=con.open_sftp()
    ftp_client.put(lfile,rfile)    
    ftp_client.close()


def download(con,lfile,rfile):
    """
    con      : ssh connection 
    lfile    : local filename to process
    rfile    : remote filename to process
    """
    print("Downloading {}".format(rfile))
    ftp_client=con.open_sftp()
    ftp_client.get(rfile,lfile)
    ftp_client.close()


def get_conn(remoteip,username):
    """
    remoteip : ssh server ip 
    username : password-less login account
    """
    homepath=str(Path.home())
    key = paramiko.RSAKey.from_private_key_file(homepath+"/.ssh/id_rsa")
    con = paramiko.SSHClient()
    con.set_missing_host_key_policy(paramiko.AutoAddPolicy())

    print("connecting")
    #con.connect(hostname=remoteip, username=username, pkey=key)
    con.connect(hostname=remoteip, username=username, password="000000", allow_agent=False)
    print("connected")

    return con


if __name__=='__main__':
    if len(sys.argv)!=4:
        print("Usage:",sys.argv[0],"user@remoteip","<remote filepath>","<local filepath>")
        sys.exit(2)

    username,remoteip=sys.argv[1].split('@')
    print(username,remoteip)

    if username==''  or remoteip=='' :
        print(sys.argv[1],"parse error!")
        sys.exit(2)

    remotefile=sys.argv[2]
    localfile=sys.argv[3]

    con=get_conn(remoteip,username)    

    upload(con,localfile,remotefile)
#    download(con,localfile,remotefile)

