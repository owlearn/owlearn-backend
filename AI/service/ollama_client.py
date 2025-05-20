import ollama
import asyncio
import json
import re

# 응답 본문에서 JSON 형식 문자열을 추출하는 함수
def extract_json_from_response(text: str) -> str:
    match = re.search(r'\{.*\}', text, re.DOTALL)
    if match:
        json_str = match.group(0)
        # ",", 패턴을 ",으로 바꿈
        return json_str.replace('",",', '",')
    else:
        raise ValueError("본문에서 JSON 블럭을 찾지 못했습니다.")

# Ollama 모델을 비동기로 호출하는 함수
async def call_ollama(prompt: str, model: str = 'deepseek-llm') -> dict:
    print("동화 생성 중...")
    def sync_chat():
        return ollama.chat(model=model, messages=[{
            'role': 'user',
            'content': prompt,
        }])

    # 동기 함수를 비동기 스레드로 실행
    response = await asyncio.to_thread(sync_chat)

    print(response["message"]["content"])

    # 응답에서 JSON 부분만 추출 후 파싱해서 딕셔너리로 반환
    try:
        json_str = extract_json_from_response(response["message"]["content"])
        return json.loads(json_str)
    except Exception as e:
        raise ValueError(f"JSON 파싱 실패: {e}")