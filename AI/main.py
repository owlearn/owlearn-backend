# $ pip install fastapi
# $ pip install "uvicorn[standard]"
# 실행 명령어: uvicorn main:app --reload

from fastapi import FastAPI

from models import TaleCreateRequest, TaleApiResponse, QuizDto
from service.tale_prompt import generate_tale_prompt
from service.image_prompt import generate_final_image_prompts
from service.ollama_client import call_ollama
from service.image_generator import generate_images

app = FastAPI()

@app.post("/api/tales", response_model=TaleApiResponse)
async def create_tale(request: TaleCreateRequest):
    # 프롬프트 생성
    prompt = generate_tale_prompt(request.topic, request.style, request.age)

    # Ollama 서버에 프롬프트 전송
    ollama_response = await call_ollama(prompt)

    # 데이터 추출
    title = ollama_response.get("title", "Untitled")
    contents = ollama_response.get("contents", [])
    quizzes_raw = ollama_response.get("quizzes", [])

    # 이미지 생성 프롬프트 정제
    image_prompts = generate_final_image_prompts(ollama_response.get("image_prompts", []), request.style)

    # 이미지 생성
    image_urls = await generate_images(image_prompts)

    # 퀴즈 파싱
    quizzes = [QuizDto(**q) for q in quizzes_raw]

    return TaleApiResponse(
        title=title,
        contents=contents,
        imageUrls=image_urls,
        quizzes=quizzes
    )