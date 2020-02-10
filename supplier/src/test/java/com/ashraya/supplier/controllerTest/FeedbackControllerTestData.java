package com.ashraya.supplier.controllerTest;

import java.util.ArrayList;
import java.util.List;

import com.ashraya.supplier.constants.Mandatory;
import com.ashraya.supplier.constants.State;
import com.ashraya.supplier.domain.FeedbackPayload;
import com.ashraya.supplier.domain.QuestionFeedbackPayload;
import com.ashraya.supplier.model.DataTypeMaster;
import com.ashraya.supplier.model.FieldTypeMaster;
import com.ashraya.supplier.model.Questions;

public class FeedbackControllerTestData {

    public static List<Questions> getAllQuetions() {
        List<Questions> questions = new ArrayList<Questions>();
        questions.add(Questions.builder().dataTypeMaster(new DataTypeMaster()).fieldTypeMaster(new FieldTypeMaster()).hint("km").mandatory(Mandatory.YES).question("Person Contact")
                        .state(State.active).questionId(7).build());
        return questions;
    }

    public static FeedbackPayload feedback() {
        List<QuestionFeedbackPayload> questionFeedbackPayload = new ArrayList<QuestionFeedbackPayload>();
        questionFeedbackPayload.add(QuestionFeedbackPayload.builder().id(7).value("YES").build());
        return FeedbackPayload.builder().orderId(12).userId(22).questionFeedbackPayload(questionFeedbackPayload).build();
    }
}
