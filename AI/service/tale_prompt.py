def generate_tale_prompt(topic: str, age: int) -> str:
    return (
        f"You are an API that only returns pure JSON.\n"
        f"Create a 5-page children's story for a {age}-year-old.\n"
        f"The theme is '{topic}'.\n"
        "Each page should be a separate string in the 'contents' array. "
        "Each page must contain exactly 2 simple sentences.\n"
        "Generate exactly 4 quiz questions based on the story.\n"
        "Each quiz must have exactly 4 answer choices, all of which are strings.\n"
        "In the 'explanation' field, explain why the correct answer is correct.\n"
        "Respond only in this JSON format:\n"
        "{\n"
        '  "title": "string",\n'
        '  "contents": ["string", "string", "string", "string", "string"],\n'
        '  "quizzes": [\n'
        "    {\n"
        '      "questionNumber": 1,\n'
        '      "question": "string",\n'
        '      "choices": ["string", "string", "string", "string"],\n'
        '      "answerIndex": int,\n'
        '      "explanation": "string (explain why the answer is correct)"\n'
        "    },\n"
        "    {\n"
        '      "questionNumber": 2,\n'
        '      "question": "string",\n'
        '      "choices": ["string", "string", "string", "string"],\n'
        '      "answerIndex": int,\n'
        '      "explanation": "string (explain why the answer is correct)"\n'
        "    },\n"
        "    {\n"
        '      "questionNumber": 3,\n'
        '      "question": "string",\n'
        '      "choices": ["string", "string", "string", "string"],\n'
        '      "answerIndex": int,\n'
        '      "explanation": "string (explain why the answer is correct)"\n'
        "    },\n"
        "    {\n"
        '      "questionNumber": 4,\n'
        '      "question": "string",\n'
        '      "choices": ["string", "string", "string", "string"],\n'
        '      "answerIndex": int,\n'
        '      "explanation": "string (explain why the answer is correct)"\n'
        "    }\n"
        "  ]\n"
        "}\n"
        "Do not include any markdown, explanation, or formatting outside of the JSON."
    )