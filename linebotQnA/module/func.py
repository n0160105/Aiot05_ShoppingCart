from django.conf import settings

from linebot import LineBotApi
from linebot.models import TextSendMessage

import http.client, json
from qnaapi.models import users

line_bot_api = LineBotApi(settings.LINE_CHANNEL_ACCESS_TOKEN)

host = 'aiot0503qna.azurewebsites.net'  #QnAmaker 主機
endpoint_key = "af15c275-4192-4f09-8ca7-cc3b6a0c3d17"  #QnA授權碼
kb = "70a7d9d9-0297-44c8-9de5-32cc0ae4295a"  #QnA GUID碼
method = "/qnamaker/knowledgebases/" + kb + "/generateAnswer"

def sendUse(event):  #使用說明
    try:
        text1 ='''
這是有關於AIOT05第三期
智能購物車的問答機器人。。
               '''
        message = TextSendMessage(
            text = text1
        )
        line_bot_api.reply_message(event.reply_token,message)
    except:
        line_bot_api.reply_message(event.reply_token,TextSendMessage(text='發生錯誤！'))

def sendQnA(event, mtext):  #QnA
    question = {
        'question': mtext, #mtext = 客戶詢問問題 , Json格式
    }
    content = json.dumps(question)
    headers = {
        'Authorization': 'EndpointKey ' + endpoint_key,
        'Content-Type': 'application/json',
        'Content-Length': len(content)
    }
    conn = http.client.HTTPSConnection(host)
    conn.request ("POST", method, content, headers)
    response = conn.getresponse ()
    result = json.loads(response.read())
    result1 = result['answers'][0]['answer']
    if 'No good match' in result1:
        text1 = '感謝您的提問，但很抱歉！\n目前我們找不到符合您需求的答案！\n煩請您聯絡賣場服務人員。'
        #將沒有解答的問題寫入資料庫
        userid = event.source.user_id
        unit = users.objects.create(uid=userid, question=mtext)
        unit.save()
    else:
        text1 = result1 
    message = TextSendMessage(
        text = text1
    )
    line_bot_api.reply_message(event.reply_token,message)
