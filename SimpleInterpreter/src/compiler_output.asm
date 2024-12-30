section .data
x dd 1
y dd 1
z dd 5
section .text
global _start
_start:
;enter_scope
; Allocating a eax for  x
mov eax, 5
; Updating the new value of x for their register
mov eax, 6
mov ebx, [y]
; Allocating ecx  register for  z
mov ecx, ebx
mov ebx, eax
; Updating the new value of x for their register
mov eax, 10
mov ebx, ecx
mov ebx, eax
;exit_scope
mov dword [x], eax
mov dword [y], ebx
mov dword [z], ecx
mov rax, 1
xor rbx, rbx
int 0x80
