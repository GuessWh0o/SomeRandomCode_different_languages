#pragma once
#include <stddef.h>
#include <cstring>
#include <iostream>


namespace Program {
	typedef unsigned int ui;
	class String {

	private:
		char* str;
		ui length;
		static int number_Elem;
	public:
		String() {
			str = NULL;
			length = 0;
		}
		String(const char* s) {
			length = strlen(s);
			str = new char[length + 1];
			for (ui i(0); i < length; i++) {
				str[i] = s[i];
				str[length] = '\0';
			}
		}

		String(const String& s) {
			length = strlen(s.str);
			str = new char[length + 1];
			for (ui i(0); i < length; i++) {
				str[i] = s.str[i];
				str[length] = '\0';
			}
		}


		friend std::ostream& operator<<(std::ostream & os, String& s) {
			os << s.str;
			return os;
		}

		String& operator=(const String& s) {
			delete[] str;
			length = strlen(s.str);
			str = new char[length + 1];
			for (ui i(0); i < length; i++) {
				str[i] = s.str[i];
				str[length] = '\0';
			}
			return *this;
		}

		operator char*() {
			char* str = new char[this->length + 1];
			for (ui i(0); i < length; i++) {
				str[i] = this->str[i];
				str[length] = '\0';
			}
			return str;
		}

		~String() {
			delete[] str;
		}
	};
}
