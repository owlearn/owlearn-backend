# $ pip install fastapi
# $ pip install "uvicorn[standard]"
# 실행 명령어: uvicorn main:app --reload

from fastapi import FastAPI
from pydantic import BaseModel
from typing import List, Optional

app = FastAPI()

# DTO 역할하는 Pydantic 모델
class QuizDto(BaseModel):
    questionNumber: int
    question: str
    choices: List[str]
    answerIndex: int
    explanation: Optional[str]

class TaleApiResponse(BaseModel):
    title: str
    contents: List[str]
    quizzes: Optional[List[QuizDto]] = []

# POST 요청 시 더미 데이터 응답
@app.post("/api/tales", response_model=TaleApiResponse)
def generate_tale():
    dummy_response = TaleApiResponse(
        title="The Magical Bird",
        contents=["Once upon a time in a deep forest, a magical blue bird.."],
        quizzes=[
            QuizDto(
                questionNumber=1,
                question="What color was the magical bird?",
                choices=["Red", "Blue", "Green", "Yellow"],
                answerIndex=1,
                explanation="The bird was described as blue."
            )
        ]
    )
    return dummy_response