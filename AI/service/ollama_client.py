async def call_ollama(prompt: str) -> dict:
    """ async with httpx.AsyncClient() as client:
        response = await client.post(
            "http://localhost:11434/api/generate",  # 수정 필요 시 이 부분 조정
            json={"prompt": prompt}
        )
        response.raise_for_status()
        return response.json() """

    # TODO: 실제 Ollama 연결 시 구현 예정
    # 테스트용 더미 데이터 반환
    return {
        "title": "Test Title3",
        "contents": [
            "Page 1: Once upon a time, in a lush green forest, lived a curious fox named Felix.",
            "Page 2: Felix found a glowing mushroom and decided to follow the lights it emitted."
        ],
        "quizzes": [
            {
                "questionNumber": 1,
                "question": "What did Felix find in the forest?",
                "choices": ["A crown", "A glowing mushroom", "A treasure chest", "A butterfly"],
                "answerIndex": 1,
                "explanation": "Felix discovered a glowing mushroom."
            },
            {
                "questionNumber": 2,
                "question": "Who helped Felix with a map?",
                "choices": ["An owl", "A bear", "A squirrel", "A rabbit"],
                "answerIndex": 2,
                "explanation": "A talking squirrel gave him the map."
            }
        ]
    }