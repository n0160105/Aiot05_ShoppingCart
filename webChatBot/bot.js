// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

const { ActivityHandler, MessageFactory } = require('botbuilder');

const { QnAMaker } = require('botbuilder-ai');

class MyBot extends ActivityHandler {
    constructor(configuration, qnaOptions) {
        super();
        if (!configuration) throw new Error('[QnaMakerBot]: Missing parameter. configuration is required');
        // now create a qnaMaker connector.
        this.qnaMaker = new QnAMaker(configuration, qnaOptions);
        // See https://aka.ms/about-bot-activity-message to learn more about the message and other activity types.
        this.onMessage(async (context, next) => {
            const qnaResults = await this.qnaMaker.getAnswers(context);
            if (qnaResults[0]) {
                await context.sendActivity(`${ qnaResults[0].answer}`); //chatbot's response
            }
            else {
                // If no answers were returned from QnA Maker, reply with help.
                //there are no answers in QnA KB
                await context.sendActivity('抱歉，我只是個機器人，您所問的問題我無法為您有效的找到正確解答，麻煩請聯絡現場服務人員，將由服務人員一對一為您解答');
            }
            await next();
        });

        this.onMembersAdded(async (context, next) => {
            const membersAdded = context.activity.membersAdded;
            const welcomeText = 'Hello ，歡迎使用Aiot05_ShoppingCart 智能客服';
            for (let cnt = 0; cnt < membersAdded.length; ++cnt) {
                if (membersAdded[cnt].id !== context.activity.recipient.id) {
                    await context.sendActivity(MessageFactory.text(welcomeText, welcomeText));
                }
            }
            // By calling next() you ensure that the next BotHandler is run.
            await next();
        });
    }
}

module.exports.MyBot = MyBot;
