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
        "title": "The Magical Adventure of Luna",
        "contents": [
            "Page 1: Luna was a curious girl who loved exploring the woods near her village. One day, she found a mysterious glowing stone.",
            "Page 2: As Luna touched the stone, a portal opened and transported her to a land full of floating islands and talking animals.",
            "Page 3: She met a friendly fox named Riko who offered to guide her through this strange and beautiful world.",
            "Page 4: Together, they crossed crystal rivers and climbed candy-colored mountains, searching for the way back home.",
            "Page 5: After solving the riddle of the Sky Tree, Luna used the glowing stone to return home, promising to visit again someday."
        ],
        "quizzes": [
            {
                "questionNumber": 1,
                "question": "What did Luna find in the woods?",
                "choices": ["A map", "A glowing stone", "A magic book", "A silver key"],
                "answerIndex": 1,
                "explanation": "Luna discovered a mysterious glowing stone in the woods."
            },
            {
                "questionNumber": 2,
                "question": "Who helped Luna explore the magical land?",
                "choices": ["A talking rabbit", "A wise owl", "A friendly fox", "A kind bear"],
                "answerIndex": 2,
                "explanation": "Riko, a friendly fox, guided Luna through the magical world."
            }
        ]
    }
