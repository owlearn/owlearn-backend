import asyncio
from service.ollama_client import call_ollama
from models import QuizDto
from service.tale_prompt import generate_tale_prompt

async def main():
    prompt = generate_tale_prompt("owl", 5)

    # await로 호출
    ollama_response = await call_ollama(prompt)

    # 결과 추출
    title = ollama_response.get("title", "Untitled")
    contents = ollama_response.get("contents", [])
    quizzes_raw = ollama_response.get("quizzes", [])
    quizzes = [QuizDto(**q) for q in quizzes_raw]

    print("\n", title)
    print("\n", contents)
    print("\n", quizzes)

# 비동기 함수 실행
if __name__ == "__main__":
    asyncio.run(main())