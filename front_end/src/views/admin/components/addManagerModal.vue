<template>
    <a-modal
            :visible="addManagerModalVisible"
            title="添加用户"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-Form :form="form">
            <a-form-item v-bind="formItemLayout" label="用户邮箱">
                <a-input
                        v-decorator="[
                        'email',
                        { rules: [{required: true, message: '请输入用户邮箱', },
                        {validator: (_, value, callback) => { //验证邮箱格式
                            if(value.length == 0){
                                return callback()
                            }
                            let patt = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/
                            if(!patt.test(value)){
                                return callback('邮箱格式错误');
                            }
                            return callback()
                        }}
                        ] }
                    ]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="密码">
                <a-input
                        v-decorator="[
                        'password',
                        { rules: [{required: true, message: '请输入密码', }] }
                    ]"
                />
            </a-form-item>
        </a-Form>
    </a-modal>
</template>
<script>
    /**
     * 添加酒店工作人员的对话框
     * */
    import {mapGetters, mapMutations, mapActions} from 'vuex'

    export default {
        name: 'addManagerModal',
        data() {
            return {
                formItemLayout: {
                    labelCol: {
                        xs: {span: 12},
                        sm: {span: 6},
                    },
                    wrapperCol: {
                        xs: {span: 24},
                        sm: {span: 16},
                    },
                },
            }
        },
        computed: {
            ...mapGetters([
                'addManagerModalVisible',
                'managerList',
                'currentHotelId'
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, {name: 'addManagerModal'});
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_addManagerModalVisible',
                'set_addManagerParams',
            ]),
            ...mapActions([
                'getManagerList',
                'addManager',
            ]),
            cancel() {
                this.set_addManagerModalVisible(false)
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            email: this.form.getFieldValue('email'),
                            password: this.form.getFieldValue('password')
                        }
                        this.set_addManagerParams(data)
                        this.addManager(this.currentHotelId)
                        this.form.resetFields()
                    }
                });
            },
        }
    }
</script>