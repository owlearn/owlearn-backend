def generate_tale_prompt(topic: str, style: str, age: int) -> str:
    return (
        f"Create a 5-page children's story for a {age}-year-old. "
        f"The theme is '{topic}',"
        "Each page should be 2~3 sentences long and easy to understand. "
        "Return JSON with: title, contents[], quizzes[]. "
        "Each quiz should include: questionNumber, question, choices, answerIndex, explanation."
    )